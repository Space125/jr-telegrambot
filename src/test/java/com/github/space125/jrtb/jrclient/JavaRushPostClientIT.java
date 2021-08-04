package com.github.space125.jrtb.jrclient;

import com.github.space125.jrtb.jrclient.dto.PostInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.space125.jrtb.jrclient.JavaRushGroupClientIT.JAVARUSH_API_PATH;

/**
 * Integration-level testing for {@link JavaRushPostClientImpl}
 *
 * @author Ivan Kurilov on 03.08.2021
 */
@DisplayName("Integration-level testing for JavaRushPostClient")
public class JavaRushPostClientIT {

    private final JavaRushPostClient javaRushPostClient = new JavaRushPostClientImpl(JAVARUSH_API_PATH);

    @Test
    void shouldProperlyGetNewPosts() {
        //when
        List<PostInfo> newPosts = javaRushPostClient.findNewPosts(30, 2935);

        //then
        Assertions.assertEquals(15, newPosts.size());
    }


}
