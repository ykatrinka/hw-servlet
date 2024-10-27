package ru.clevertec.edu.ykv.service;

import ru.clevertec.edu.ykv.dto.RocketRequest;
import ru.clevertec.edu.ykv.dto.RocketResponse;
import ru.clevertec.edu.ykv.entity.Rocket;
import ru.clevertec.edu.ykv.mapper.RocketMapper;
import ru.clevertec.edu.ykv.repository.Repository;
import ru.clevertec.edu.ykv.repository.RocketRepository;

import java.util.List;
import java.util.UUID;

public class RocketService implements Service<RocketResponse, RocketRequest> {

    private static final RocketMapper ROCKET_MAPPER = RocketMapper.INSTANCE;
    private final Repository<Rocket> rocketRepository = new RocketRepository();

    @Override
    public RocketResponse save(RocketRequest rocketRequest) {
        Rocket rocket = ROCKET_MAPPER.requestToEntity(rocketRequest);
        Rocket savedRocket = rocketRepository.save(rocket);

        return ROCKET_MAPPER.entityToResponse(savedRocket);
    }

    @Override
    public RocketResponse findByUuid(UUID uuid) {
        Rocket rocket = rocketRepository.findByUuid(uuid);
        return ROCKET_MAPPER.entityToResponse(rocket);
    }

    @Override
    public List<RocketResponse> findAll() {
        List<Rocket> rockets = rocketRepository.findAll();
        return rockets.stream()
                .map(ROCKET_MAPPER::entityToResponse)
                .toList();
    }

    @Override
    public RocketResponse update(UUID rocketUuid, RocketRequest rocketRequest) {
        Rocket rocket = ROCKET_MAPPER.requestToEntity(rocketRequest);
        rocket = rocketRepository.update(rocketUuid, rocket);

        return ROCKET_MAPPER.entityToResponse(rocket);
    }

    @Override
    public void delete(UUID rocketUuid) {
        rocketRepository.delete(rocketUuid);
    }
}
