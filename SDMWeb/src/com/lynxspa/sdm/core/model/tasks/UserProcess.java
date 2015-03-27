package com.lynxspa.sdm.core.model.tasks;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("U")
public class UserProcess extends SystemProcess {

	private static final long	serialVersionUID	= -188207612889975626L;

	public UserProcess() {
		super(null);
	}

	public UserProcess(final String _user) {
		super(_user);
	}
}
