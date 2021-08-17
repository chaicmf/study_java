package com.cmf.design.AbstractFactory.factory;

import com.cmf.design.AbstractFactory.InterfaceController;
import com.cmf.design.AbstractFactory.OperationController;
import com.cmf.design.AbstractFactory.SymbianInterfaceController;
import com.cmf.design.AbstractFactory.SymbianOperationController;
import com.cmf.design.AbstractFactory.factory.PhoneFactory;

public class SymbianFactory implements PhoneFactory {

    @Override
    public OperationController createOperationController() {
        return new SymbianOperationController();
    }

    @Override
    public InterfaceController createInterfaceController() {
        return new SymbianInterfaceController();
    }
}
