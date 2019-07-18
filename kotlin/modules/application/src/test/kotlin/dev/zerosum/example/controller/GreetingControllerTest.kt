package dev.zerosum.example.controller

import dev.zerosum.example.greeting.GreetingService
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class GreetingControllerTest {

    @MockBean
    private lateinit var greetingService: GreetingService

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun hello() {
        given(greetingService.greet()).willReturn("__any_greet__")

        mockMvc.perform(get("/"))
                .andExpect(status().isOk)
                .andExpect(content().string("__any_greet__"))
    }
}