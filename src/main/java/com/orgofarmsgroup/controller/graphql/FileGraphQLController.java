package com.orgofarmsgroup.controller.graphql;

import com.orgofarmsgroup.dto.misc.FileOperations;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@Slf4j
public class FileGraphQLController {
    private final ModelMapper modelMapper;

    @QueryMapping(name = "files")
    public FileOperations usersQuery() {
        log.info("file graphql controller : files query : accessed");
        return new FileOperations();
    }

    @MutationMapping(name = "files")
    public FileOperations usersMutation() {
        log.info("file graphql controller: files mutation : accessed");
        return new FileOperations();
    }

    @SchemaMapping(typeName = "FileOperations", field = "upload")
    public boolean upload(@Argument(name = "file") MultipartFile file) {
        log.info("file graphql controller: files.upload mutation : accessed");
        try{
            log.info("processing multipart file");
        }catch (Exception ex) {
            log.error("error occurred while processing multipart file");
        }
        return true;
    }
}
