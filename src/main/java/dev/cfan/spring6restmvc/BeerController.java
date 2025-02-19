package dev.cfan.spring6restmvc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BeerController {

    private final BeerService beerService;

    @PostMapping("/api/v1/beer")
    public String getBeer(@RequestBody String type) {

        return beerService.getBeer(type);
    }

}
