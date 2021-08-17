package com.cmf.design.AbstractFactory.factory;

import com.cmf.design.AbstractFactory.InterfaceController;
import com.cmf.design.AbstractFactory.OperationController;
import com.cmf.design.AbstractFactory.WindowsInterfaceController;
import com.cmf.design.AbstractFactory.WindowsOperationController;
import com.cmf.design.AbstractFactory.factory.PhoneFactory;

public class WindowsMobileFacoory implements PhoneFactory {

    @Override
    public OperationController createOperationController() {
        return new WindowsOperationController();
    }

    @Override
    public InterfaceController createInterfaceController() {
        return new WindowsInterfaceController();
    }
}
