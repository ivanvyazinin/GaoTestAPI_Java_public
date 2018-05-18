package main.java.api.contentCloud;

import java.util.Map;

public class RequestParameters {
    private String requestParameters;

    public void setRequestParameters(Map<String, String> hashmap){
        StringBuilder params = new StringBuilder();
        requestParameters = "";

        if (!hashmap.isEmpty()){
            for (String key : hashmap.keySet()){
                switch (key){
                    case "embed":
                        params.append("&embed[]=").append(hashmap.get(key));break;
                    case "pagination":
                        params.append("&pagination=").append(hashmap.get(key));break;
                    case "per_page":
                        params.append("&per_page=").append(hashmap.get(key)); break;
                    case "page":
                        params.append("&page=").append(hashmap.get(key)); break;
                    case "sorting":
                        params.append("&order[").append(hashmap.get(key)).append("]="); break;
                    case "order":
                        params.append(hashmap.get(key)); break;
                }
            }
            requestParameters =  params.replace(0,1,"?").toString();
        }
    }

    public String getRequestParameters() {
        return requestParameters;
    }

    public void resetRequestParameters() {
        requestParameters = "";
    }
}
