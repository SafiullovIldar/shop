package com.shop.demo.service;

import com.shop.demo.config.AppConfig;
import com.shop.demo.dao.PromoCodeDao;
import com.shop.demo.dto.PromoCodeDto;
import com.shop.demo.entity.PromoCode;
import com.shop.demo.util.Util;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class PromoCodeServiceTest {

    @Autowired
    private ConversionService conversionService;

    @Mock
    private PromoCodeDao promoCodeDao;

    @Autowired
    @InjectMocks
    private PromoCodeService promoCodeService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenCreatePromoCodeCalledVerified() {
        PromoCodeDto promoCodeDto = Util.createTestPromoCode("1");
        PromoCode promoCode = conversionService.convert(promoCodeDto, PromoCode.class);

        doNothing().when(promoCodeDao).createPromoCode(promoCode);
        promoCodeService.createPromoCode(promoCodeDto);

        verify(promoCodeDao, times(1)).createPromoCode(promoCode);
    }

    @Test
    public void shouldReturnPromoCodeWithExpectedPromoCodeId() {
        PromoCodeDto promoCodeDto = Util.createTestPromoCode("55");
        PromoCode promoCode = conversionService.convert(promoCodeDto, PromoCode.class);

        when(promoCodeDao.getPromoCode("55")).thenReturn(promoCode);

        assertEquals("55", promoCodeService.getPromoCode("55").getId());
        assertNotNull(promoCodeService.getPromoCode("55"));
    }
}