package com.rs2.ecommerce.product;

import com.rs2.ecommerce.utils.CriteriaParser;
import com.rs2.ecommerce.utils.GenericSpecificationsBuilder;
import com.rs2.ecommerce.utils.PaginatedResultsRetrievedEvent;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

import static com.rs2.ecommerce.utils.Util.dtoMapper;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController implements ProductAPI {
    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public ProductDTO createProduct(ProductDTO product) {
        return dtoMapper(productService.createProduct(dtoMapper(product, Product.class, modelMapper)),
                ProductDTO.class, modelMapper);
    }

    @Override
    public ProductDTO getProduct(long productId) {
        return dtoMapper(productService.getProduct(productId),
                ProductDTO.class, modelMapper);
    }

    @Override
    public void deleteProduct(long productId) {
        productService.deleteProduct(productId);
    }

    @Override
    public ProductDTO updateProduct(long productId, ProductDTO product) {
        return dtoMapper(productService.updateProduct(productId, dtoMapper(product, Product.class, modelMapper)),
                ProductDTO.class, modelMapper);
    }

    @Override
    public ResponseEntity<PagedModel<ProductDTO>> search(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response, String searchQuery) {
        Specification<Product> spec = resolveSpecificationFromInfixExpr(searchQuery);
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                ProductDTO.class, uriBuilder, response, pageable.getPageNumber()
                , productService.getProducts(pageable).getTotalPages(), pageable.getPageSize()));

        return new ResponseEntity<PagedModel<ProductDTO>>(assembler.toModel(productService.search(spec,pageable).map(product -> dtoMapper(product, ProductDTO.class, modelMapper))),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PagedModel<ProductDTO>> getProducts(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                ProductDTO.class, uriBuilder, response, pageable.getPageNumber()
                , productService.getProducts(pageable).getTotalPages(), pageable.getPageSize()));

        return new ResponseEntity<PagedModel<ProductDTO>>(assembler.toModel(productService.getProducts(pageable).map(product -> dtoMapper(product, ProductDTO.class, modelMapper))),
                HttpStatus.OK);
    }

    protected Specification<Product> resolveSpecificationFromInfixExpr(String searchParameters) {
        var parser = new CriteriaParser();
        GenericSpecificationsBuilder<Product> specBuilder = new GenericSpecificationsBuilder<>();
        return specBuilder.build(parser.parse(searchParameters), ProductSpecification::new);
    }
}
