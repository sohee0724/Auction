package Auction.service.controller;

import Auction.service.aop.product.ProductCheck;
import Auction.service.dto.ProductDto;
import Auction.service.dto.Result;
import Auction.service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

import static Auction.service.utils.ResultCode.SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @ProductCheck
    @PostMapping
    public ResponseEntity<Result> uploadProduct(@RequestPart(value = "images", required = false) List<MultipartFile> images,
                                                @RequestPart(value = "productDto") @Valid ProductDto productDto,
                                                BindingResult bindingResult) {
        productService.upload(productDto, images);
        return Result.toResponseEntity(SUCCESS);
    }

}
