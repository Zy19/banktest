package com.bmo.ianlipliavyi.db.controller;


import com.bmo.ianlipliavyi.db.dao.UserDao;
import com.bmo.ianlipliavyi.db.model.UserDBModel;
import com.bmo.ianlipliavyi.db.utils.Converter;
import com.bmo.ianlipliavyi.global.BmoConstants;
import com.bmo.ianlipliavyi.global.model.UserModel;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RequestMapping(BmoConstants.URL_USER)
@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(method = RequestMethod.GET, params = {"search"})
    @ResponseBody
    public List<UserModel> getByTripUid(@RequestParam(required = false, name = "search") final String search) {

        final List<UserDBModel> db = this.userDao.searchFirstChars(search.toLowerCase());

        final List<UserModel> list = Lists.newArrayList();

        db.forEach(d -> list.add(Converter.convert(d)));

        return list;
    }


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<UserModel> getByTripUid() {

        final Iterable<UserDBModel> db = this.userDao.findAll();

        final List<UserModel> list = Lists.newArrayList();

        db.forEach(d -> list.add(Converter.convert(d)));

        return list;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity<?> saveMessage(final UriComponentsBuilder b,
                                         @RequestBody final UserModel model) throws URISyntaxException {

        UserDBModel userDBModel = Converter.convert(model);

        this.userDao.save(userDBModel);
        model.setUid(userDBModel.getUid());

        final UriComponents uriComponents = UriComponentsBuilder.fromPath("/{uid}").buildAndExpand(userDBModel.getUid());

        final HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return ResponseEntity.created(uriComponents.toUri()).headers(headers).body(model);
    }
}
