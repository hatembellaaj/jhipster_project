package com.csf.gaf.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.csf.gaf.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ChevalTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cheval.class);
        Cheval cheval1 = new Cheval();
        cheval1.setId(1L);
        Cheval cheval2 = new Cheval();
        cheval2.setId(cheval1.getId());
        assertThat(cheval1).isEqualTo(cheval2);
        cheval2.setId(2L);
        assertThat(cheval1).isNotEqualTo(cheval2);
        cheval1.setId(null);
        assertThat(cheval1).isNotEqualTo(cheval2);
    }
}
