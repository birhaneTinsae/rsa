package com.rs2.ecommerce.product;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

public interface ProductAPI {
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    ProductDTO createProduct(@Valid @RequestBody ProductDTO product);

    @GetMapping("/{productId}")
    ProductDTO getProduct(@PathVariable long productId);

    @DeleteMapping("/{productId}")
    void deleteProduct(@PathVariable long productId);

    @PutMapping("/{productId}")
    ProductDTO updateProduct(@PathVariable long productId, @Valid @RequestBody ProductDTO product);

    @GetMapping("/search")
    ResponseEntity<PagedModel<ProductDTO>> search(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                  @Valid Pageable pageable
            , PagedResourcesAssembler assembler
            , UriComponentsBuilder uriBuilder

            , final HttpServletResponse response, @RequestParam(required = false) String searchQuery);


    @GetMapping()
    ResponseEntity<PagedModel<ProductDTO>> getProducts(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                       @Valid Pageable pageable
            , PagedResourcesAssembler assembler
            , UriComponentsBuilder uriBuilder

            , final HttpServletResponse response);
}
