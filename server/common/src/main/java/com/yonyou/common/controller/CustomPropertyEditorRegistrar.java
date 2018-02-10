package com.yonyou.common.controller;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

/**
 * @author itc.chenzhi
 * @version 创建时间：2011-6-8 上午10:59:41
 * 自定义属性编辑器注册
 */
public class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar {

    public void registerCustomEditors(PropertyEditorRegistry registry) {

        registry.registerCustomEditor(java.util.Date.class, new DateEditor());

    }
}
