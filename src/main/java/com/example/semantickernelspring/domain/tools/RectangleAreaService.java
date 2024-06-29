package com.example.semantickernelspring.domain.tools;

import java.util.function.Function;

public class RectangleAreaService implements Function<RectangleAreaService.Request, RectangleAreaService.Response> {

    // Request for RectangleAreaService
    public record Request(double base, double height) {}
    public record Response(double area) {}

    @Override
    public Response apply(Request r) {
        return new Response(r.base()*r.height());
    }

}
