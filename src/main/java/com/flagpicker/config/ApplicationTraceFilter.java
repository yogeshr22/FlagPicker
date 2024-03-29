package com.flagpicker.config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.actuate.trace.http.HttpExchangeTracer;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.web.trace.servlet.HttpTraceFilter;
import org.springframework.stereotype.Component;

@Component
public class ApplicationTraceFilter extends HttpTraceFilter {

  public ApplicationTraceFilter(HttpTraceRepository repository, HttpExchangeTracer tracer) {
    super(repository, tracer);
  }
  
  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
      return request.getServletPath().contains("actuator");
  }

}
