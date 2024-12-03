package com.ed.myFirstEdProject;

import com.ed.myFirstEdProject.entity.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class UserArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(User.builder().userName("aravind").password("jithu").build()),
                Arguments.of(User.builder().userName("ajay").password("").build()));
    }
}
