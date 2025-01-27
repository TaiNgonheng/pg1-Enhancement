package com.rhbgroup.dte.pg1enhancement.service.ifc;

import com.rhbgroup.dte.pg1enhancement.model.LoginDto;
import com.rhbgroup.dte.pg1enhancement.model.RegisterDto;

import java.util.Set;

public interface AuthServiceifc {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
    Set<?> name(LoginDto loginDto);
}
