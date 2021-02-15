package com.ebi.person.constant;

public interface PersonParameter {
	
	  public static final String CONTROLLERS = "execution(* com.ebi.person.controller.*.*(..))";
      public static final String SERVICES = "execution(* com.ebi.person.service.*.*(..))";
      public static final String REPOSITORY = "execution(* com.ebi.person.data.repository.*.*(..))";

}
