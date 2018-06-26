//package com.duckduckgogogo.services.impl;
//
//import com.duckduckgogogo.services.TraceSearchService;
//import org.springframework.http.converter.StringHttpMessageConverter;
//import org.springframework.stereotype.Service;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
//import javax.transaction.Transactional;
//import java.nio.charset.StandardCharsets;
//
//public class TraceServiceImpl extends Info implements TraceSearchService {
//
//    @Override
//    public String getTraceInfo(int personID, String dateStrat, String dateEnd) {
//        try{
//            String url = "http://" + super.serverIP + "/api/query/camera";
//
//            RestTemplate rest = new RestTemplate();
//
//            MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
//            param.add("personID", personID);
//            param.add("dateStrat", dateStrat);
//            param.add("dateEnd", dateEnd);
//
//            rest.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
//
//            return rest.postForObject(url, param, String.class);
//
//        } catch (Exception e){
//            return "error : "+e.getMessage();
//        }
//    }
//}
