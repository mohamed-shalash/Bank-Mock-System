package com.fawry.bank.mapper.Impl;

import com.fawry.bank.entity.Address;
import com.fawry.bank.mapper.AddressMapper;
import com.fawry.bank.models.AddressModule;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddressMapperImpl implements AddressMapper {

    @Override
    public Address addressToModuleMapper(AddressModule module) {
        return Address .builder()
                .City(module.getCity())
                .Country(module.getCountry())
                .State(module.getState())
                .HouseID(module.getHouseID())
                .Streate(module.getStreate()).build();

    }

    @Override
    public AddressModule moduleToAddressMapper(Address address) {
        return AddressModule.builder()
                .City(address.getCity())
                .Country(address.getCountry())
                .State(address.getState())
                .HouseID(address.getHouseID())
                .Streate(address.getStreate())
                .build();
    }
}
