package com.assignment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

	private int id;
	private String name;
	private String username;
	private Address address;
	private String phone;
    private String website;
    private Company company;
    
    public User() {
    	
    }
    public User(int id, String name, String username, Address address, String phone, String website, Company company) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.company = company;
    }
    
	@JsonProperty("address")
    public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@JsonProperty("phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@JsonProperty("website")
	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@JsonProperty("company")
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@JsonProperty("id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public static class Address {
		private String street;
        private String suite;
        private String city;
        private String zipcode;
        private Geo geo;
        
        public Address() {
        	
        }
        public Address(String street, String suite, String city, String zipcode, Geo geo) {
            this.street = street;
            this.suite = suite;
            this.city = city;
            this.zipcode = zipcode;
            this.geo = geo;
        }
        
        public String getStreet() {
			return street;
		}
		public void setStreet(String street) {
			this.street = street;
		}
		public String getSuite() {
			return suite;
		}
		public void setSuite(String suite) {
			this.suite = suite;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getZipcode() {
			return zipcode;
		}
		public void setZipcode(String zipcode) {
			this.zipcode = zipcode;
		}
		public Geo getGeo() {
			return geo;
		}
		public void setGeo(Geo geo) {
			this.geo = geo;
		}
   }

    public static class Geo {
        private String lat;
		private String lng;
		public Geo() {
			
		}
		public Geo(String lat, String lng) {
	        this.lat = lat;
	        this.lng = lng;
	    }
		
        public String getLat() {
			return lat;
		}
		public void setLat(String lat) {
			this.lat = lat;
		}
		public String getLng() {
			return lng;
		}
		public void setLng(String lng) {
			this.lng = lng;
		}

    }
    public static class Company {
    	private String name;
        private String catchPhrase;
        private String bs;
        public Company() {
        	
        }
        public Company(String name, String catchPhrase, String bs) {
            this.name = name;
            this.catchPhrase = catchPhrase;
            this.bs = bs;
        }
        
        public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCatchPhrase() {
			return catchPhrase;
		}
		public void setCatchPhrase(String catchPhrase) {
			this.catchPhrase = catchPhrase;
		}
		public String getBs() {
			return bs;
		}
		public void setBs(String bs) {
			this.bs = bs;
		}
    }

}
