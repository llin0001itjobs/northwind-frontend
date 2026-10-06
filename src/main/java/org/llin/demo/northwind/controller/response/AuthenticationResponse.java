package org.llin.demo.northwind.controller.response;

import org.llin.demo.northwind.model.entity.Authentication;
import org.llin.demo.northwind.model.entity._EntityObject;

public class AuthenticationResponse extends BaseResponse {

    private Authentication[] authentications;

    public Authentication[] getAuthentications() {
        return authentications;
    }

    public void setAuthentications(Authentication[] authentications) {
        this.authentications = authentications;
    }

	@Override
	public _EntityObject[] getResponse() {
		// TODO Auto-generated method stub
		return authentications;
	}
    
}
