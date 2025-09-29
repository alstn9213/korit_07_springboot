package com.example.cardatabase4.web;

import com.example.cardatabase4.domain.Owner;
import com.example.cardatabase4.service.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OwnerController {
    private final OwnerService ownerService;
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }


    @GetMapping("/owners")
    public List<Owner> getOwner() {
        return ownerService.getOwners();
    }


    @PostMapping("/owners")
    public ResponseEntity<Owner> addOwner(@RequestBody Owner owner) {
        Owner savedOwner = ownerService.addOwner(owner);
        return new ResponseEntity<>(savedOwner, HttpStatus.CREATED);
    }

    @GetMapping("/owners/{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable Long id) {
        return ownerService.getOwnerById(id)
                .map(owner -> ResponseEntity.ok().body(owner)) // 차량 정보가 있으면 200 OK를 반환할 것
                .orElse(ResponseEntity.notFound().build()); // 없으면 404 Not Found를 return 시킬 것
    }


    @DeleteMapping("/owners/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
        if(ownerService.deleteOwner(id)) {
            return ResponseEntity.noContent().build(); // 삭제 성공시 204 No content return
        } else {
            return ResponseEntity.notFound().build(); // 대상이 없으면 404 Not Found
        }
    }


    @PutMapping("/owners/{id}")
    public ResponseEntity<Owner> updateOwner(@PathVariable Long id, @RequestBody Owner ownerDetails) {
        return ownerService.updateOwner(id, ownerDetails)
                .map(updateOwner -> ResponseEntity.ok().body(updateOwner)) // 수정 성공시에는 200OK
                .orElse(ResponseEntity.notFound().build()); // 수정할 차량 id 검색 실패시 404 Not Found
    }
}
