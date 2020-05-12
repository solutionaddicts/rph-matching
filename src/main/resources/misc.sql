
select * from rph_prop_lstg where (pets_amenities #>>'{arePetsAllowed}'):: boolean = true;



select * from rph_prop_lstg where (kitchen_amenities #>>'{dishWasherAvailableInUnit}'):: boolean = true;


select * from rph_prop_lstg where (transport_parking_amenities #>>'{driveWay}'):: boolean = true;


select * from rph_prop_lstg where (transport_parking_amenities #>>'{electricCarChargingStationAvailable}'):: boolean = true;


select * from rph_prop_lstg where  (community_amenities #>> '{elevator}'):: boolean = true;


select * from rph_prop_lstg where  (other_amenities #>> '{fireplace}'):: boolean = true;


select * from rph_prop_lstg where (around_the_neighbourhood #>> '{fitnessCenter}'):: boolean = true


select * from rph_prop_lstg where (furniture #>>'{furnished}'):: boolean = true;


select * from rph_prop_lstg where (transport_parking_amenities #>>'{garage}'):: boolean = true;

select * from rph_prop_lstg where (tech_electric_amenities #>>'{airConditionAvailable}'):: boolean = true  and
           (tech_electric_amenities #>>'{heatingAvailable}'):: boolean = true;



select * from rph_prop_lstg where (laundry_amenities #>>'{free}'):: boolean = true;


select * from rph_prop_lstg where  (other_amenities #>> '{lgbtqFriendly}'):: boolean = true;


select * from rph_prop_lstg where (rent_deposit #>> '{minLeaseDurationVal}')::integer > 4 and
        (rent_deposit #>> '{maxLeaseDurationVal}')::integer > 10


select * from rph_prop_lstg where (rent_deposit #>>'{brokerFee}'):: double precision > 0.00;


select * from rph_prop_lstg where (transport_parking_amenities #>>'{onStreet}'):: boolean = true;

select * from rph_prop_lstg where  (other_amenities #>> '{patio}'):: boolean = true;


select pets_amenities from rph_prop_lstg lstg where pets_amenities->'petTypesAllowed'  @> '["Cat"]'::jsonb;


select * from rph_prop_lstg where (transport_parking_amenities #>>'{privateLot}'):: boolean = true;


select * from rph_prop_lstg where (rent_deposit #>> '{monthlyRent}')::double precision between 1000 and 10000;


select tech_electric_amenities from rph_prop_lstg where  (tech_electric_amenities #>> '{airConditionAvailable}'):: boolean = true;

select * from rph_prop_lstg where (utilities_included_in_the_rent #>> '{electricity}'):: boolean = true and
        (utilities_included_in_the_rent #>> '{heating}'):: boolean = true;

select * from rph_prop_lstg where diet_pref = 'Vegetarian';