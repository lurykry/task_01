package edu.parammanagment.parammanagment;

import edu.parammanagment.parammanagment.domain.core.Parameter;
import edu.parammanagment.parammanagment.repository.AbstractRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ParameterManagementApplicationTests {

	@Autowired
	private MockMvc mvc;
	@MockBean
	private AbstractRepository<Parameter> repository;

	@Test
	void testEndPoints() throws Exception {
		this.mvc
				.perform(get("/parameter/a65f377e-bd22-4a87-b9f3-8f26ead6ff3d"))
				.andExpect(status().isOk())
				.andExpect(content()
						.contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	void testRepositories() {
		assertThat(
				this.repository.findById(UUID.fromString("a65f377e-bd22-4a87-b9f3-8f26ead6ff3d"))
						.get()
						.getParamName())
						.isEqualTo("P_2e");
	}

}
