package com.noirix.controller.springdata;

import com.noirix.controller.requests.RoleRequest;
import com.noirix.controller.requests.UserCreateRequest;
import com.noirix.domain.Gender;
import com.noirix.domain.hibernate.HibernateRole;
import com.noirix.domain.hibernate.HibernateUser;
import com.noirix.repository.jdbctemplate.RoleRepositoryInterface;
import com.noirix.repository.springdata.RolesSpringDataRepository;
import com.noirix.repository.springdata.UserSpringDataRepository;
import com.noirix.security.util.PrincipalUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/data/users")
public class UserController {

    private final UserSpringDataRepository repository;

    private final RolesSpringDataRepository rolesSpringDataRepository;

    private final RoleRepositoryInterface roleRepository;

    private final ConversionService converter;

    @GetMapping
    public ResponseEntity<Object> testEndpoint() {

        return new ResponseEntity<>(Collections.singletonMap("result",
                //repository.findAll(PageRequest.of(0, 10))), HttpStatus.OK);
                repository.findByIsDeletedOrderByIdDesc(false)), HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<Object> testEndpointSearchQuery(@RequestParam("id") Long userId, @RequestParam("gender") String gender) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                repository.findByIdAndGender(userId, Gender.valueOf(gender))), HttpStatus.OK);
    }

    @ApiOperation(value = "Finding all users with Page Info response")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", defaultValue = "token", required = true, paramType = "header", dataType = "string"),
            @ApiImplicitParam(name = "query", defaultValue = "query", required = false, paramType = "query", dataType = "string")

//            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
//                    value = "Results page you want to retrieve (0..N)"),
//            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
//                    value = "Number of records per page."),
//            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
//                    value = "Sorting criteria in the format: property(,asc|desc). " +
//                            "Default sort order is ascending. " +
//                            "Multiple sort criteria are supported.")
    })
    @GetMapping("/swagger-test")
    public ResponseEntity<Page<HibernateUser>> findAll(@ApiIgnore Principal principal) {
        String username = PrincipalUtil.getUsername(principal);
        HibernateUser userByPrincipal = repository.findByCredentialsLogin(username);

        System.out.println(userByPrincipal);
        return new ResponseEntity<>(repository.findAll(PageRequest.of(0, 10)), HttpStatus.OK);
    }


    @PostMapping
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 100, rollbackFor = Exception.class)
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserCreateRequest createRequest) {

        RoleRequest roleRequest = new RoleRequest();

        HibernateUser user = converter.convert(createRequest, HibernateUser.class);
        HibernateUser createdUser = repository.save(setRoles(user));

        HibernateRole convertTest = converter.convert(roleRequest, HibernateRole.class);
        //repository.createRoleRow(createdUser.getId(), roleRepository.findById(1L).getId());

        Map<String, Object> model = new HashMap<>();
        model.put("user", repository.findById(createdUser.getId()).get());

        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }

    private HibernateUser setRoles(HibernateUser user) {
        Set<HibernateRole> roles = user.getRoles();

        Set<HibernateRole> updatedRoles = new HashSet<>();

        if (!CollectionUtils.isEmpty(roles)) {
            updatedRoles.addAll(roles);
        }
        updatedRoles.add(rolesSpringDataRepository.findById(1L).get());
        updatedRoles.add(rolesSpringDataRepository.findById(2L).get());

        user.setRoles(updatedRoles);

        return user;
    }
}
