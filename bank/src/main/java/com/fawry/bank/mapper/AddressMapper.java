package com.fawry.bank.mapper;

import com.fawry.bank.entity.Address;
import com.fawry.bank.models.AddressModule;

public interface AddressMapper {
    Address addressToModuleMapper(AddressModule module);
    AddressModule moduleToAddressMapper(Address module);
}
