package com.bmo.ianlipliavyi.users.controller;

import com.bmo.ianlipliavyi.global.BmoConstants;
import com.bmo.ianlipliavyi.global.model.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RequestMapping("/v1")
@RestController
public class UserController {

    @Value("${service.db.host}")
    private String dbHost;

    protected final RestTemplate restTemplate = BmoConstants.createRestTemplate();

    @RequestMapping(method = RequestMethod.GET, params = {"search"})
    @ResponseBody
    public List<UserModel> getBySearch(@RequestParam(required = false, name = "search") final String search) {

        final StringBuilder b = new StringBuilder();

        b.append(dbHost);
        b.append(BmoConstants.URL_USER);
        b.append("?search=");
        b.append(search);

        final ResponseEntity<List<UserModel>> response = this.restTemplate.exchange(b.toString(), HttpMethod.GET, null,
                new ParameterizedTypeReference<List<UserModel>>() {
                });

        List<UserModel> list = response.getBody();

        list.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));

        return list;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<UserModel> getByAll() {

        final StringBuilder b = new StringBuilder();

        b.append(dbHost);
        b.append(BmoConstants.URL_USER);

        final ResponseEntity<List<UserModel>> response = this.restTemplate.exchange(b.toString(), HttpMethod.GET, null,
                new ParameterizedTypeReference<List<UserModel>>() {
                });

        List<UserModel> list = response.getBody();

        list.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));

        return list;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<?> saveMessage(final UriComponentsBuilder b,
                                         @RequestBody final UserModel model) throws URISyntaxException {

        final HttpEntity<UserModel> requestEntity = new HttpEntity<>(model);

        final StringBuilder builder = new StringBuilder();

        builder.append(dbHost);
        builder.append(BmoConstants.URL_USER);

        final ResponseEntity<UserModel> ret = this.restTemplate.exchange(
                new URI(builder.toString()), HttpMethod.PUT, requestEntity, UserModel.class);

        UserModel result = ret.getBody();

        final UriComponents uriComponents = UriComponentsBuilder.fromPath("/{uid}").buildAndExpand(result.getUid());

        final HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return ResponseEntity.created(uriComponents.toUri()).headers(headers).body(result);
    }
}