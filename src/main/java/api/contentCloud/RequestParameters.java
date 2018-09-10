package main.java.api.contentCloud;

import java.util.Map;

public class RequestParameters {
    private String requestParameters= "";

    public void setRequestParameters(Map<String, String> hashmap){
        StringBuilder params = new StringBuilder();
        requestParameters = "";

        if (!hashmap.isEmpty()){
            hashmap.forEach( (key,value) -> {
                        switch (key){
                            case "search":
                                params.append("&search=").append(value);break;
                            case "itemType":
                                params.append("&itemType=").append(value);break;
                            case "parentFolder":
                                params.append("&parentFolder=").append(value);break;
                            case "embed":
                                params.append("&embed[]=").append(value);break;
                            case "pagination":
                                params.append("&pagination=").append(value);break;
                            case "per_page":
                                params.append("&per_page=").append(value); break;
                            case "page":
                                params.append("&page=").append(value); break;
                            case "sorting":
                                params.append("&order[").append(value).append("]="); break;
                            case "order":
                                params.append(value); break;
                        }
                    }
            );
            requestParameters =  params.replace(0,1,"?").toString();
        }
    }

    public void setRequestParameters(String[][] parameters){
        StringBuilder params = new StringBuilder();
        requestParameters = "";

        if (parameters.length!=0){
            for (String[] parametersSet :parameters){
                switch (parametersSet[0]){
                    case "search":
                        params.append("&search=").append(parametersSet[1]);break;
                    case "itemType":
                        params.append("&itemType=").append(parametersSet[1]);break;
                    case "parentFolder":
                        params.append("&parentFolder=").append(parametersSet[1]);break;
                    case "embed":
                        params.append("&embed[]=").append(parametersSet[1]);break;
                    case "pagination":
                        params.append("&pagination=").append(parametersSet[1]);break;
                    case "per_page":
                        params.append("&per_page=").append(parametersSet[1]); break;
                    case "page":
                        params.append("&page=").append(parametersSet[1]); break;
                    case "sorting":
                        params.append("&order[").append(parametersSet[1]).append("]="); break;
                    case "order":
                        params.append(parametersSet[1]); break;

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
