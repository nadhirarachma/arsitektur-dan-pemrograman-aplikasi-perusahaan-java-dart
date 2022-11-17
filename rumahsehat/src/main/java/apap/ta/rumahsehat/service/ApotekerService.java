package apap.ta.rumahsehat.service;

import apap.ta.rumahsehat.model.ApotekerModel;

import java.util.List;

public interface ApotekerService {
    ApotekerModel addApoteker(ApotekerModel apoteker);
    public String encrypt(String password);
    ApotekerModel getApotekerByUsername(String username);
    List<ApotekerModel> getListApoteker();
}