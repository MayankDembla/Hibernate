package org.dembla.validator;

import org.dembla.model.Employee;
import org.hibernate.validator.HibernateValidator;

import javax.validation.*;
import javax.validation.executable.ExecutableValidator;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.Set;

public class ValidatorTest {

    public static void main(String[] args) throws FileNotFoundException, NoSuchMethodException {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        System.out.println("\nSimple field level validation example");

        EmployeeBeanValidator emp = new EmployeeBeanValidator(-1, "Name","email","123");

        Set<ConstraintViolation<EmployeeBeanValidator>> validationErrors = validator.validate(emp);

        if(!validationErrors.isEmpty()){
            for(ConstraintViolation<EmployeeBeanValidator> error : validationErrors){
                System.out.println(error.getMessageTemplate()+"::"+error.getPropertyPath()+"::"+error.getMessage());
            }
        }

        //XML Based validation
//        Configuration<?> config = Validation.byDefaultProvider().configure();
//        config.addMapping(new FileInputStream("./src/main/resources/employeeXMLValidation.xml"));
//        ValidatorFactory validatorFactory1 = config.buildValidatorFactory();
//        Validator validator1 = validatorFactory1.getValidator();
//
//        EmployeeXmlValidator emp1 = new EmployeeXmlValidator(10, "Name","email","123");
//
//        Set<ConstraintViolation<EmployeeXmlValidator>> validationErrors1 = validator1.validate(emp1);
//
//        if(!validationErrors1.isEmpty()){
//            for(ConstraintViolation<EmployeeXmlValidator> error : validationErrors1){
//                System.out.println(error.getMessageTemplate()+"::"+error.getInvalidValue()+"::"+ error.getPropertyPath()+"::"+error.getMessage());
//            }
//        }

        System.out.println("\nValidation with inheritance example");

        //Validation inheritance and Property-level constraints example
        MyChildBean childBean = new MyChildBean();
        Set<ConstraintViolation<MyChildBean>> validationInheritanceErrors = validator.validate(childBean);

        if(!validationInheritanceErrors.isEmpty()){
            for(ConstraintViolation<MyChildBean> error : validationInheritanceErrors){
                System.out.println(error.getMessageTemplate()+"::"+error.getPropertyPath()+"::"+error.getMessage());

            }
        }

        System.out.println("\nValidation in composition using @Valid annotation");

        //@Valid annotation - validation in composition example
        AnotherBean compositionBean = new AnotherBean();
        compositionBean.setChildBean(new MyChildBean());
        Set<ConstraintViolation<AnotherBean>> validationCompositionErrors = validator.validate(compositionBean);

        if(!validationCompositionErrors.isEmpty()){
            for(ConstraintViolation<AnotherBean> error : validationCompositionErrors){
                System.out.println(error.getMessageTemplate()+"::"+error.getPropertyPath()+"::"+error.getMessage());

            }
        }

        System.out.println("\nParameter validation example");
        ParamValidationBean paramValidationBean = new ParamValidationBean("Pankaj");
        Method method = ParamValidationBean.class.getMethod("printData", String.class);
        Object[] params = {"1234"};
        ExecutableValidator executableValidator = validator.forExecutables();
        Set<ConstraintViolation<ParamValidationBean>> violations =
                executableValidator.validateParameters(paramValidationBean, method, params);
        if(!violations.isEmpty()){
            for(ConstraintViolation<ParamValidationBean> error : violations){
                System.out.println(error.getMessageTemplate()+"::"+error.getPropertyPath()+"::"+error.getMessage());

            }
        }

    }
}
