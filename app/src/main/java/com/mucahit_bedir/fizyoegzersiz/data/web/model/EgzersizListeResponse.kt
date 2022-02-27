package com.mucahit_bedir.fizyoegzersiz.data.web.model

data class EgzersizListeResponse(
    val name: String,
    val imageURL: String,
    val egzersizler: List<ItemEgzersizler>
) {
    data class ItemEgzersizler(
        val name: String,
        var aciklama: String? = "boş",
        val seviye: Int,
        val videolar: List<EgzersizVideo>
    ) {
        data class EgzersizVideo(
            val name: String,
            val imageURL: String,
            val videoURL: String
        )
    }

    companion object{
        fun mockEgzersizListeResponse(): ArrayList<EgzersizListeResponse>{
            return arrayListOf<EgzersizListeResponse>(
                EgzersizListeResponse(
                    name = "Sırt Ağrısı",
                    imageURL = "https://acilci.net/wp-content/uploads/2016/11/sirt-agrilari-neden-olur-300x149.jpg",
                    egzersizler = listOf(
                        ItemEgzersizler(
                            name = "Seviye 1",
                            seviye = 1,
                            aciklama = "GÜnlük",
                            videolar = listOf(
                                ItemEgzersizler.EgzersizVideo(
                                    name = "video 1",
                                    imageURL = "https://acilci.net/wp-content/uploads/2016/11/sirt-agrilari-neden-olur-300x149.jpg",
                                    videoURL = "http://techslides.com/demos/sample-videos/small.mp4"
                                ),
                                ItemEgzersizler.EgzersizVideo(
                                    name = "video 2",
                                    imageURL = "https://acilci.net/wp-content/uploads/2016/11/sirt-agrilari-neden-olur-300x149.jpg",
                                    videoURL = "http://techslides.com/demos/sample-videos/small.mp4"
                                )
                            )
                        ),
                        ItemEgzersizler(
                            name = "Seviye 2",
                            seviye = 2,
                            aciklama = "orta",
                            videolar = listOf(
                                ItemEgzersizler.EgzersizVideo(
                                    name = "video 1",
                                    imageURL = "https://acilci.net/wp-content/uploads/2016/11/sirt-agrilari-neden-olur-300x149.jpg",
                                    videoURL = "http://techslides.com/demos/sample-videos/small.mp4"
                                ),
                                ItemEgzersizler.EgzersizVideo(
                                    name = "video 2",
                                    imageURL = "https://acilci.net/wp-content/uploads/2016/11/sirt-agrilari-neden-olur-300x149.jpg",
                                    videoURL = "http://techslides.com/demos/sample-videos/small.mp4"
                                )
                            )
                        ),
                        ItemEgzersizler(
                            name = "Seviye 3",
                            seviye = 3,
                            aciklama = "ileri",
                            videolar = listOf(
                                ItemEgzersizler.EgzersizVideo(
                                    name = "video 1",
                                    imageURL = "https://acilci.net/wp-content/uploads/2016/11/sirt-agrilari-neden-olur-300x149.jpg",
                                    videoURL = "http://techslides.com/demos/sample-videos/small.mp4"
                                ),
                                ItemEgzersizler.EgzersizVideo(
                                    name = "video 2",
                                    imageURL = "https://acilci.net/wp-content/uploads/2016/11/sirt-agrilari-neden-olur-300x149.jpg",
                                    videoURL = "http://techslides.com/demos/sample-videos/small.mp4"
                                )
                            )
                        )

                    )
                ),
                EgzersizListeResponse(
                    name = "Bel Ağrısı",
                    imageURL = "https://iasbh.tmgrup.com.tr/5b4fcc/650/344/0/94/596/407?u=https://isbh.tmgrup.com.tr/sbh/2020/02/10/bel-agrisina-ne-iyi-gelir-bel-agrisi-nasil-gecer-egzersiz-ile-gecer-mi-1581324866941.jpg",
                    egzersizler = listOf(
                        ItemEgzersizler(
                            name = "",
                            seviye = 1,
                            videolar = listOf(
                                ItemEgzersizler.EgzersizVideo(
                                    name = "",
                                    imageURL = "",
                                    videoURL = ""
                                )
                            )
                        )
                    )
                ),
                EgzersizListeResponse(
                    name = "Omuz Ağrısı",
                    imageURL = "https://www.doktorfizik.com/wp-content/uploads/2019/05/sag-omuz-agrisi-nedenleri.jpg",
                    egzersizler = listOf(
                        ItemEgzersizler(
                            name = "",
                            seviye = 1,
                            videolar = listOf(
                                ItemEgzersizler.EgzersizVideo(
                                    name = "",
                                    imageURL = "",
                                    videoURL = ""
                                )
                            )
                        )
                    )
                ),
                EgzersizListeResponse(
                    name = "Diz Ağrısı",
                    imageURL = "https://i01.sozcucdn.com/wp-content/uploads/2019/10/07/iecrop/diz-shutterstock_16_9_1570455831-670x371.jpg",
                    egzersizler = listOf(
                        ItemEgzersizler(
                            name = "",
                            seviye = 1,
                            videolar = listOf(
                                ItemEgzersizler.EgzersizVideo(
                                    name = "",
                                    imageURL = "",
                                    videoURL = ""
                                )
                            )
                        )
                    )
                ),
                EgzersizListeResponse(
                    name = "Boyun Ağrısı",
                    imageURL = "https://service.drsistem.com/media/ec96c921-b664-439b-b876-3376c62f4308_0a256b1b-3a72-467e-9151-b62406c7f957.jpg",
                    egzersizler = listOf(
                        ItemEgzersizler(
                            name = "",
                            seviye = 1,
                            videolar = listOf(
                                ItemEgzersizler.EgzersizVideo(
                                    name = "",
                                    imageURL = "",
                                    videoURL = ""
                                )
                            )
                        )
                    )
                )
            )
        }
    }

}

