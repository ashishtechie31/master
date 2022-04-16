package com.springboot.app.beanLifeCycle;

import java.util.Arrays;

import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.spotify.docker.client.shaded.javax.annotation.PostConstruct;
/**
 * 
 * @author Ashish Gupta
 *
 */
public class CustomBeanLifeCycle implements BeanNameAware,ApplicationContextAware,InitializingBean,DisposableBean,BeanPostProcessor {


	public void initMethod() {
	    System.out.println("--- init-method executed ---");
	  }

	  @PreDestroy
	  public void preDestroy() {
	    System.out.println("--- @PreDestroy executed ---");
	  }

	  @Override
	  public void destroy() throws Exception {
	    System.out.println("--- destroy executed ---");
	  }

	  public void destroyMethod() {
	    System.out.println("--- destroy-method executed ---");
	  }

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("CustomBeanLifeCycle.afterPropertiesSet()");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {		
		System.out.println("CustomBeanLifeCycle.setApplicationContext() "+Arrays.toString(applicationContext.getBeanDefinitionNames()));
	}

	@Override
	public void setBeanName(String beanValue) {
		System.out.println("CustomBeanLifeCycle.setBeanName() "+beanValue);
		
	}
	
	@PostConstruct
	 public void postConstruct() {
	    System.out.println("--- @PostConstruct executed ---");
	  }

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("CustomBeanLifeCycle.postProcessBeforeInitialization()");
		return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	}
	
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("CustomBeanLifeCycle.postProcessAfterInitialization() ");
		return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}
		
}
