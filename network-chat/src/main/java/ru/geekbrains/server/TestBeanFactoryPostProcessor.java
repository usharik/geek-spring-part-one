package ru.geekbrains.server;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class TestBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] definitionNames = beanFactory.getBeanDefinitionNames();

        for (String name : definitionNames) {
            System.out.println(beanFactory.getBeanDefinition(name));
        }
    }
}
