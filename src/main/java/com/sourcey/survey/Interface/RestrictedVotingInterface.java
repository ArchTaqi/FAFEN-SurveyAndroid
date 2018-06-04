package com.sourcey.survey.Interface;

public interface RestrictedVotingInterface {
    public void callFromRestrictedFragment(boolean questionThreeAsnwered, String ans3);
    public void onErrorRestrictedValidation(boolean questionThreeAsnwered);
}
