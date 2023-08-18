package com.powerledger.api;

import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@Rollback
public class BatteryApiTest extends BaseTest {
    @Test
    public void testCreateBattery_Success() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder request = buildSuccessfulCreateRequest();

        // Act
        ResultActions results = this.mockMvc.perform(request);

        // Assert
        results.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(this.mediaType));
    }

    @Test
    public void testCreateBattery_Fail() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder request = buildFailedCreateRequest();

        // Act
        ResultActions results = this.mockMvc.perform(request);

        // Assert
        results.andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().contentType(this.mediaType));
    }

    @Test
    public void testFetchBatteries_NoFilterSuccess() throws Exception {
        // Seed the DB
        seedWithValidRecords();

        // Arrange the GET request
        MockHttpServletRequestBuilder getRequest = get("/batteries").accept(this.mediaType);

        // Action the requests
        ResultActions getResults = this.mockMvc.perform(getRequest);

        // Assert
        getResults.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(this.mediaType))
            .andExpect(jsonPath("$.count").value("20"))
            .andExpect(jsonPath("$.totalCapacity").value("539000"))
            .andExpect(jsonPath("$.averageCapacity").value("26950"));
    }

    @Test
    public void testFetchBatteries_WithAllFiltersSuccess() throws Exception {
        // Seed the DB
        seedWithValidRecords();

        // Arrange the GET request
        MockHttpServletRequestBuilder getRequest = get("/batteries")
                .queryParam("minPostcode", "2000")
                .queryParam("maxPostcode", "6000")
                .accept(this.mediaType);

        // Action the requests
        ResultActions getResults = this.mockMvc.perform(getRequest);

        // Assert
        getResults.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(this.mediaType))
                .andExpect(jsonPath("$.count").value("7"))
                .andExpect(jsonPath("$.totalCapacity").value("176000"))
                .andExpect(jsonPath("$.averageCapacity").value("25142"));
    }

    @Test
    public void testFetchBatteries_MinFilterOnlySuccess() throws Exception {
        // Seed the DB
        seedWithValidRecords();

        // Arrange the GET request
        MockHttpServletRequestBuilder getRequest = get("/batteries")
                .queryParam("minPostcode", "3000")
                .accept(this.mediaType);

        // Action the requests
        ResultActions getResults = this.mockMvc.perform(getRequest);

        // Assert
        getResults.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(this.mediaType))
                .andExpect(jsonPath("$.count").value("14"))
                .andExpect(jsonPath("$.totalCapacity").value("444500"))
                .andExpect(jsonPath("$.averageCapacity").value("31750"));
    }

    @Test
    public void testFetchBatteries_MaxFilterOnlySuccess() throws Exception {
        // Seed the DB
        seedWithValidRecords();

        // Arrange the GET request
        MockHttpServletRequestBuilder getRequest = get("/batteries")
                .queryParam("maxPostcode", "4000")
                .accept(this.mediaType);

        // Action the requests
        ResultActions getResults = this.mockMvc.perform(getRequest);

        // Assert
        getResults.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(this.mediaType))
                .andExpect(jsonPath("$.count").value("7"))
                .andExpect(jsonPath("$.totalCapacity").value("179500"))
                .andExpect(jsonPath("$.averageCapacity").value("25642"));
    }

    @Test
    public void testFetchBattery_NotFound() throws Exception {
        // Seed the DB
        seedWithValidRecords();
        String id = UUID.randomUUID().toString();

        // Arrange the GET request
        MockHttpServletRequestBuilder getRequest = get("/batteries/" + id).accept(this.mediaType);

        // Action the requests
        ResultActions getResults = this.mockMvc.perform(getRequest);

        // Assert
        getResults.andDo(print()).andExpect(status().isNotFound());
    }

    private MockHttpServletRequestBuilder buildSuccessfulCreateRequest() throws IOException {
        MockHttpServletRequestBuilder postRequest = post("/batteries");
        return postRequest.content(this.testData.getContentAsByteArray())
                .accept(this.mediaType)
                .contentType(this.mediaType);
    }

    private MockHttpServletRequestBuilder buildFailedCreateRequest() throws IOException {
        MockHttpServletRequestBuilder postRequest = post("/batteries");
        return postRequest.content(this.badData.getContentAsByteArray())
                .accept(this.mediaType)
                .contentType(this.mediaType);
    }

    private void seedWithValidRecords() throws Exception {
        MockHttpServletRequestBuilder request = buildSuccessfulCreateRequest();
        ResultActions results = this.mockMvc.perform(request);
        results.andExpect(status().isOk());
    }
}