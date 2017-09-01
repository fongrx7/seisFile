package edu.sc.seis.seisFile.fdsnws.stationxml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import edu.sc.seis.seisFile.fdsnws.StaxUtil;

public class Station extends BaseNodeType {

    public Station() {}
    
    public Station(Network network, String code) {
    		this.network = network;
        this.networkCode = network.getNetworkCode();
        this.networkId = network.getNetworkId();
        this.code = code;
    }
    
    public Station(XMLEventReader reader, Network network) throws XMLStreamException, StationXMLException {
        this.network = network;
        this.networkCode = network.getNetworkCode();
        this.networkId = network.getNetworkId();
        StartElement startE = StaxUtil.expectStartElement(StationXMLTagNames.STATION, reader);
        super.parseAttributes(startE);
        while (reader.hasNext()) {
            XMLEvent e = reader.peek();
            if (e.isStartElement()) {
                String elName = e.asStartElement().getName().getLocalPart();
                if (super.parseSubElement(elName, reader)) {
                    // super handled it
                } else if (elName.equals(StationXMLTagNames.LAT)) {
                    latitude = new FloatType(reader, StationXMLTagNames.LAT, Unit.DEGREE);
                } else if (elName.equals(StationXMLTagNames.LON)) {
                    longitude = new FloatType(reader, StationXMLTagNames.LON, Unit.DEGREE);
                } else if (elName.equals(StationXMLTagNames.ELEVATION)) {
                    elevation = new FloatType(reader, StationXMLTagNames.ELEVATION, Unit.METER);
                } else if (elName.equals(StationXMLTagNames.SITE)) {
                    site = new Site(reader);
                } else if (elName.equals(StationXMLTagNames.VAULT)) {
                    vault = StaxUtil.pullText(reader, StationXMLTagNames.VAULT);
                } else if (elName.equals(StationXMLTagNames.GEOLOGY)) {
                    geology = StaxUtil.pullText(reader, StationXMLTagNames.GEOLOGY);
                } else if (elName.equals(StationXMLTagNames.EQUIPMENT)) {
                    equipmentList.add(new Equipment(reader));
                } else if (elName.equals(StationXMLTagNames.OPERATOR)) {
                    operatorList.add(new Operator(reader));
                } else if (elName.equals(StationXMLTagNames.CREATIONDATE)) {
                    creationDate = StaxUtil.pullText(reader, StationXMLTagNames.CREATIONDATE);
                } else if (elName.equals(StationXMLTagNames.TERMINATIONDATE)) {
                    terminationDate = StaxUtil.pullText(reader, StationXMLTagNames.TERMINATIONDATE);
                } else if (elName.equals(StationXMLTagNames.TOTALNUMCHANNELS)) {
                    totalNumChannels = StaxUtil.pullInt(reader, StationXMLTagNames.TOTALNUMCHANNELS);
                } else if (elName.equals(StationXMLTagNames.SELECTEDNUMCHANNELS)) {
                    selectedNumChannels = StaxUtil.pullInt(reader, StationXMLTagNames.SELECTEDNUMCHANNELS);
                } else if (elName.equals(StationXMLTagNames.EXTERNALREFERENCE)) {
                    externalReferenceList.add(StaxUtil.pullText(reader, StationXMLTagNames.EXTERNALREFERENCE));
                } else if (elName.equals(StationXMLTagNames.CHANNEL)) {
                    channelList.add(new Channel(reader, this));
                } else {
                    StaxUtil.skipToMatchingEnd(reader);
                }
            } else if (e.isEndElement()) {
                reader.nextEvent();
                return;
            } else {
                e = reader.nextEvent();
            }
        }
    }
    
    @Deprecated
    public Network getNetworkAttr() {
        return getNetwork();
    }
    public Network getNetwork() {
        return network;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public FloatType getLatitude() {
        return latitude;
    }

    public FloatType getLongitude() {
        return longitude;
    }

    public FloatType getElevation() {
        return elevation;
    }

    @Deprecated
    public String getName() {
        return name;
    }

    public Site getSite() {
        return site;
    }

    public int getTotalNumChannels() {
        return totalNumChannels;
    }

    public int getSelectedNumChannels() {
        return selectedNumChannels;
    }

    public List<Channel> getChannelList() {
        return channelList;
    }

    public String getTerminationDate() {
        return terminationDate;
    }

    public String getVault() {
        return vault;
    }

    public String getGeology() {
        return geology;
    }

    @Deprecated
    public String getNetworkCode() {
        return networkCode;
    }

    public String getNetworkId() {
        return networkId;
    }
    
    public String getStationCode() {
        return getCode();
    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public List<Operator> getOperatorList() {
        return operatorList;
    }

    public List<String> getExternalReferenceList() {
        return externalReferenceList;
    }

    @Override
    public String toString() {
        return getNetworkCode()+"."+getCode();
    }

    public void setNetwork(Network network) {
        this.network = network;
    }
    
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    
    public void setTerminationDate(String terminationDate) {
        this.terminationDate = terminationDate;
    }

    public void setLatitude(float latitude) {
    		setLatitude(new FloatType(latitude, Unit.DEGREE));
    }
    
    public void setLatitude(FloatType latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
    	    setLongitude(new FloatType(longitude, Unit.DEGREE));
    }
    
    public void setLongitude(FloatType longitude) {
        this.longitude = longitude;
    }

    /** set elevation in METERS. */
    public void setElevation(float elevation) {
        setElevation(new FloatType(elevation, Unit.METER));
    }
    
    public void setElevation(FloatType elevation) {
        this.elevation = elevation;
    }

    @Deprecated
    public void setName(String name) {
        this.name = name;
    }

    
    public void setVault(String vault) {
        this.vault = vault;
    }

    
    public void setGeology(String geology) {
        this.geology = geology;
    }

    
    public void setSite(Site site) {
        this.site = site;
    }

    
    public void setTotalNumChannels(int totalNumChannels) {
        this.totalNumChannels = totalNumChannels;
    }

    
    public void setSelectedNumChannels(int selectedNumChannels) {
        this.selectedNumChannels = selectedNumChannels;
    }

    
    public void setNetworkCode(String networkCode) {
        this.networkCode = networkCode;
    }

    
    public void setChannelList(List<Channel> channelList) {
        this.channelList = channelList;
    }

    
    public void setEquipmentList(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }


    public void setOperatorList(List<Operator> operatorList) {
        this.operatorList = operatorList;
    }
    
    public void addOperator(Operator operator) {
        this.operatorList.add(operator);
    }

    
    public void setExternalReferenceList(List<String> externalReferenceList) {
        this.externalReferenceList = externalReferenceList;
    }
    
    Network network;

    String creationDate, terminationDate;

    FloatType latitude, longitude, elevation;

    String name;

    String vault;

    String geology;

    Site site;

    int totalNumChannels;

    int selectedNumChannels;

    String networkCode;
    
    String networkId;

    List<Channel> channelList = new ArrayList<Channel>();

    List<Equipment> equipmentList = new ArrayList<Equipment>();
    
    List<Operator> operatorList = new ArrayList<Operator>();

    List<String> externalReferenceList = new ArrayList<String>();
}
