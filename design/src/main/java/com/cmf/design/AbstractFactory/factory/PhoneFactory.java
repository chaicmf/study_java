package com.cmf.design.AbstractFactory.factory;

import com.cmf.design.AbstractFactory.InterfaceController;
import com.cmf.design.AbstractFactory.OperationController;

public  interface PhoneFactory {
    public OperationController createOperationController();
    public InterfaceController createInterfaceController();

}
