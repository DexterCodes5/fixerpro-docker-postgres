package dev.dex.fixerprodockerpostgres.model;

public record RecaptchaResponse(Boolean success,
                                String challenge_ts,
                                Double score,
                                String action) {
}
