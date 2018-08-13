package com.ymlinks.boot;

import com.ymlinks.boot.dto.MaDto;
import com.ymlinks.boot.dto.MbDto;
import org.springframework.beans.BeanUtils;

public class BootTest {

    public static void main(String[] args) {
//        Integer a = new Integer(1);
//        System.out.println(a ==1);


        MaDto maDto = new MaDto((byte)1, 1);
        MbDto mbDto = new MbDto();
        BeanUtils.copyProperties(maDto, mbDto);
        System.out.println(mbDto);
    }
}
