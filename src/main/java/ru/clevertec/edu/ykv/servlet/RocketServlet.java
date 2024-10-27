package ru.clevertec.edu.ykv.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.edu.ykv.dto.RocketRequest;
import ru.clevertec.edu.ykv.dto.RocketResponse;
import ru.clevertec.edu.ykv.mapper.RocketMapper;
import ru.clevertec.edu.ykv.service.RocketService;
import ru.clevertec.edu.ykv.service.Service;
import ru.clevertec.edu.ykv.util.Constants;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "rocket-servlet", value = "/rocket")
public class RocketServlet extends HttpServlet {

    private final Service<RocketResponse, RocketRequest> rocketService = new RocketService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RocketRequest rocketRequest = RocketMapper.INSTANCE
                .httpRequestToRocketRequest(request);
        RocketResponse savedRocket = rocketService.save(rocketRequest);

        writeValue(response, savedRocket);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter(Constants.PARAM_ACTION);

        if (Constants.ACTION_FIND_BY_UUID.equals(action)) {
            getByUuid(request, response);
        } else if (Constants.ACTION_FIND_ALL.equals(action)) {
            getAll(response);
        }

    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UUID uuid = UUID.fromString(request.getParameter(Constants.PARAM_UUID));
        RocketRequest rocketRequest = RocketMapper.INSTANCE
                .httpRequestToRocketRequest(request);
        RocketResponse updatedRocket = rocketService.update(uuid, rocketRequest);

        writeValue(response, updatedRocket);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        UUID uuid = UUID.fromString(request.getParameter(Constants.PARAM_UUID));
        rocketService.delete(uuid);

        response.setStatus(Constants.STATUS_OK);
    }


    private void getAll(HttpServletResponse response) throws IOException {
        List<RocketResponse> rockets = rocketService.findAll();
        writeValue(response, rockets);
    }

    private void getByUuid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UUID uuid = UUID.fromString(request.getParameter(Constants.PARAM_UUID));
        RocketResponse rocket = rocketService.findByUuid(uuid);

        writeValue(response, rocket);
    }

    private void writeValue(HttpServletResponse response, Object object) throws IOException {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.writeValue(response.getWriter(), object);
    }
}
