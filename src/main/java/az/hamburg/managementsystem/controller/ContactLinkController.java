package az.hamburg.managementsystem.controller;

import az.hamburg.managementsystem.model.contactlink.request.ContactLinkCreateRequest;
import az.hamburg.managementsystem.model.contactlink.request.ContactLinkUpdateRequest;
import az.hamburg.managementsystem.model.contactlink.response.ContactLinkCreateResponse;
import az.hamburg.managementsystem.model.contactlink.response.ContactLinkReadResponse;
import az.hamburg.managementsystem.model.contactlink.response.ContactLinkUpdateResponse;
import az.hamburg.managementsystem.service.ContactLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/contact-links")
@RequiredArgsConstructor
public class ContactLinkController {

    private final ContactLinkService contactLinkService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ContactLinkReadResponse> findAll() {
        return contactLinkService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContactLinkReadResponse findById(@PathVariable Long id) {
        return contactLinkService.getById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ContactLinkCreateResponse create(@RequestBody ContactLinkCreateRequest contactLink) {
        return contactLinkService.create(contactLink);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContactLinkUpdateResponse update(@PathVariable Long id, @RequestBody ContactLinkUpdateRequest contactLink) {
        return contactLinkService.update(id, contactLink);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  delete(@PathVariable Long id) {
        contactLinkService.delete(id);
    }


}
