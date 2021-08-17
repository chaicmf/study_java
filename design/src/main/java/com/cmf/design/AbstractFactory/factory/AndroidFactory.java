package com.cmf.design.AbstractFactory.factory;

import com.cmf.design.AbstractFactory.*;

public class AndroidFactory implements PhoneFactory {

    @Override
    public OperationController createOperationController() {

        return new AndroidOperationController() ;
    }

    @Override
    public InterfaceController createInterfaceController() {
        return new AndroidInterfaceController();
    }
}
