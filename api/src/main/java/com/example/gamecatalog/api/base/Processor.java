package com.example.gamecatalog.api.base;

public interface Processor<I extends Request, R extends Response> {
    R process(I request);

}
