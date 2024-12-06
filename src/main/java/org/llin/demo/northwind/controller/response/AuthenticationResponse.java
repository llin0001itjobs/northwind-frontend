package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.Authentication;
import org.llin.demo.northwind.model.BaseObject;

public class AuthenticationResponse extends BaseResponse {

    private Authentication[] authentications;

    public Authentication[] getAuthentications() {
        return authentications;
    }

    public void setAuthentications(Authentication[] authentications) {
        this.authentications = authentications;
    }

	@Override
	public BaseObject[] getResponse() {
		// TODO Auto-generated method stub
		return authentications;
	}
    
}
