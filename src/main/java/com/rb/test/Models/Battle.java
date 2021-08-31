package com.rb.test.Models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@JsonSerialize
public class Battle {

    private String battle_id;
    private String user_ip;
    private String user_server_region;
    private String server_version;
    private String player_name;
    private String event_name;
    private String event_timestamp;
    private String user_device_country;
    private String user_id;
    private String user_type;
    private String client_version;
    private String is_premium;
    private String platform;
    private String user_is_spender;
    private String outcome;
    private String ship_destroyed;

    public Battle(String battle_id, String user_ip, String user_server_region, String server_version, String player_name, String event_name,
                  String event_timestamp, String user_device_country, String user_id, String user_type, String client_version, String is_premium, String platform, String user_is_spender, String outcome, String ship_destroyed) {
        this.battle_id = battle_id;
        this.user_ip = user_ip;
        this.user_server_region = user_server_region;
        this.server_version = server_version;
        this.player_name = player_name;
        this.event_name = event_name;
        this.event_timestamp = event_timestamp;
        this.user_device_country = user_device_country;
        this.user_id = user_id;
        this.user_type = user_type;
        this.client_version = client_version;
        this.is_premium = is_premium;
        this.platform = platform;
        this.user_is_spender = user_is_spender;
        this.outcome = outcome;
        this.ship_destroyed = ship_destroyed;
    }

    public String getBattle_id() {
        return battle_id;
    }

    public void setBattle_id(String battle_id) {
        this.battle_id = battle_id;
    }

    public String getUser_ip() {
        return user_ip;
    }

    public void setUser_ip(String user_ip) {
        this.user_ip = user_ip;
    }

    public String getUser_server_region() {
        return user_server_region;
    }

    public void setUser_server_region(String user_server_region) {
        this.user_server_region = user_server_region;
    }

    public String getServer_version() {
        return server_version;
    }

    public void setServer_version(String server_version) {
        this.server_version = server_version;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_timestamp() {
        return event_timestamp;
    }

    public void setEvent_timestamp(String event_timestamp) {
        this.event_timestamp = event_timestamp;
    }

    public String getUser_device_country() {
        return user_device_country;
    }

    public void setUser_device_country(String user_device_country) {
        this.user_device_country = user_device_country;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getClient_version() {
        return client_version;
    }

    public void setClient_version(String client_version) {
        this.client_version = client_version;
    }

    public String getIs_premium() {
        return is_premium;
    }

    public void setIs_premium(String is_premium) {
        this.is_premium = is_premium;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getUser_is_spender() {
        return user_is_spender;
    }

    public void setUser_is_spender(String user_is_spender) {
        this.user_is_spender = user_is_spender;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getShip_destroyed() {
        return ship_destroyed;
    }

    public void setShip_destroyed(String ship_destroyed) {
        this.ship_destroyed = ship_destroyed;
    }
}
