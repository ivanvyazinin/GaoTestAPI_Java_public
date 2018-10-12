package main.java.api.contentCloud;

public class RequestParameters {
    private String requestParameters= "";

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
                    case "level":
                        params.append("&level=").append(parametersSet[1]); break;
                    case "language":
                        params.append("&language.id[]=").append(parametersSet[1]); break;
                    case "skill":
                        params.append("&skill.id[]=").append(parametersSet[1]); break;
                    case "eqf":
                        params.append("&eqf.id[]=").append(parametersSet[1]); break;
                    case "license":
                        params.append("&license.id[]=").append(parametersSet[1]); break;
                    case "isco":
                        params.append("&isco.id[]=").append(parametersSet[1]); break;
                    case "zone":
                        params.append("&zone.id[]=").append(parametersSet[1]); break;
                    case "study":
                        params.append("&study.id[]=").append(parametersSet[1]); break;
                    case "status":
                        params.append("&status[]=").append(parametersSet[1]); break;
                    case "role":
                        params.append("&roles.id[]=").append(parametersSet[1]); break;
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
