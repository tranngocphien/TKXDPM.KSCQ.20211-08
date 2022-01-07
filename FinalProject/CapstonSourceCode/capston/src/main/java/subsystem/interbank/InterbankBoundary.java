package subsystem.interbank;

import utils.API;

public class InterbankBoundary {

    String query(String url, String data) {
        String response = null;
        try {
            response = API.post(url, data);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return response;
    }

}
