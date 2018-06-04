package com.sourcey.survey.Interface;

public interface TimerInterface {
    public void callFromFragment(boolean questionThreeAsnwered,String ans3,boolean exceedsTimeLimit);
    public void onErrorValidation(boolean questionThreeAsnwered,boolean exceedsTimeLimit);
}
