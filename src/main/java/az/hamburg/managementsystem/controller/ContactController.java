package az.hamburg.managementsystem.controller;

import az.hamburg.managementsystem.model.contact.request.ContactCreateRequest;
import az.hamburg.managementsystem.model.contact.request.ContactUpdateRequest;
import az.hamburg.managementsystem.model.contact.response.ContactCreateResponse;
import az.hamburg.managementsystem.model.contact.response.ContactReadResponse;
import az.hamburg.managementsystem.model.contact.response.ContactUpdateResponse;
import az.hamburg.managementsystem.service.ContactService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/contacts")
@RequiredArgsConstructor
@Tag(name = "Contact Controller API", description = "Managing Contact Apis")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class ContactController {

    private final ContactService contactService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    private ContactCreateResponse create(@RequestBody ContactCreateRequest createRequest) {
        return contactService.create(createRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContactReadResponse getId(@PathVariable Long id) {

        return contactService.getId(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ContactReadResponse> getAll() {

        return contactService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {

        contactService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContactUpdateResponse update(@PathVariable Long id, @RequestBody ContactUpdateRequest updateRequest) {
        return contactService.update(id, updateRequest);
    }
}
