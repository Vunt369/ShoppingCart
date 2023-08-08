/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vunt.registration;

import java.io.Serializable;

/**
 *
 * @author NguyenTuanVu
 */
public class RegistrationCreateError implements Serializable{
    private String userNameLengthError;
    private String passwordLengthError;
    private String confirmNotMatch;
    private String fullNameLengthError;
    private String usenameIsExisted;

    public RegistrationCreateError() {
    }
    
    
    
    /**
     * @return the userNameLengthError
     */
    public String getUserNameLengthError() {
        return userNameLengthError;
    }

    /**
     * @param userNameLengthError the userNameLengthError to set
     */
    public void setUserNameLengthError(String userNameLengthError) {
        this.userNameLengthError = userNameLengthError;
    }

    /**
     * @return the passwordLengthError
     */
    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    /**
     * @param passwordLengthError the passwordLengthError to set
     */
    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }

    /**
     * @return the confirmNotMatch
     */
    public String getConfirmNotMatch() {
        return confirmNotMatch;
    }

    /**
     * @param confirmNotMatch the confirmNotMatch to set
     */
    public void setConfirmNotMatch(String confirmNotMatch) {
        this.confirmNotMatch = confirmNotMatch;
    }

    /**
     * @return the fullNameLengthError
     */
    public String getFullNameLengthError() {
        return fullNameLengthError;
    }

    /**
     * @param fullNameLengthError the fullNameLengthError to set
     */
    public void setFullNameLengthError(String fullNameLengthError) {
        this.fullNameLengthError = fullNameLengthError;
    }

    /**
     * @return the usenameIsExisted
     */
    public String getUsenameIsExisted() {
        return usenameIsExisted;
    }

    /**
     * @param usenameIsExisted the usenameIsExisted to set
     */
    public void setUsenameIsExisted(String usenameIsExisted) {
        this.usenameIsExisted = usenameIsExisted;
    }
    
    
}
