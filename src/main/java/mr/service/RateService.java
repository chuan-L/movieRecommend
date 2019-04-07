package mr.service;

import mr.entity.Rate;
import mr.mapper.RateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LiChuan on 2019/2/20.
 */
@Service
public class RateService {

    @Autowired
    private RateMapper rateMapper;

    public Integer insertRate(Rate r) throws Exception{


        return rateMapper.insert(r);
    }


}
